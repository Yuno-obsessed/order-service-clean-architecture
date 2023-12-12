import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";

export const authApi = createApi({
  reducerPath: "authApi",
  baseQuery: fetchBaseQuery({
    baseUrl: "http://localhost:8003/api/v1/auth",
  }),
  endpoints: (builder) => ({
    loginUser: builder.mutation({
      query: ({ email, password }) => {
        return {
          url: "/login",
          method: "POST",
          withCredentials: true,
          body: {
            email: email,
            password: password,
          },
        };
      },
    }),
    checkToken: builder.mutation({
      query: ({token}) => {
        return {
          url: '/access',
          method: 'POST',
          body: {
            access_token: token
          }
        }
      }
    })
  }),
});

export const { useLoginUserMutation, useCheckTokenMutation } = authApi;
