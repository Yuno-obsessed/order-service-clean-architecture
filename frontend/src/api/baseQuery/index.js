import { fetchBaseQuery } from '@reduxjs/toolkit/query/react';
export const baseQueryWithReauth = ({baseUrl}) => {
    const baseQuery = fetchBaseQuery({ baseUrl, prepareHeaders });

    let didAttemptRefresh = false;

    async function prepareHeaders(headers, { getState }) {
        const token = localStorage.getItem('token');
        if (token) {
            headers.set('authorization', `${token}`);
        }
        return headers;
    }

    return async (args, api, extraOptions) => {
        let result = await baseQuery(args, api, extraOptions);
        if (result.error?.status === 500 && !didAttemptRefresh) {
            didAttemptRefresh = true;
            const refreshResult = await baseQuery({ url: 'http://localhost:8003/api/v1/auth/refresh-token', method: 'GET' }, api, extraOptions);

            if (refreshResult.data) {
                localStorage.setItem('token', refreshResult.data.token);
                result = await baseQuery(args, api, extraOptions);
            }
        }
        setTimeout(() => { didAttemptRefresh = false; }, 1000);
        return result;
    };
};
