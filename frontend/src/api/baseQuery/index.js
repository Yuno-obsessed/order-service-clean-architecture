import { fetchBaseQuery } from '@reduxjs/toolkit/query/react';

export const baseQueryWithReauth = ({baseUrl}) => {
    const baseQuery = fetchBaseQuery({
        baseUrl,
        prepareHeaders: (headers, { getState }) => {
            const token = localStorage.getItem('token');
            if (token) {
                headers.set('authorization', `${token}`);
            }
            return headers;
        },
    });
    return async (args, api, extraOptions) => {
        let result = await baseQuery(args, api, extraOptions);
        if (result?.error?.status === 500) {
            const refreshResult = await baseQuery({
                url: 'http://localhost:8003/api/v1/auth/refresh-token',
                method: 'GET',
            }, api, extraOptions);
            if (refreshResult.data) {
                localStorage.setItem('token', refreshResult.data.token);
                result = await baseQuery(args, api, extraOptions);
            }

        }
        return result;
    };
};

