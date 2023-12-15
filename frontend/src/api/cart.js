import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";

export const productApi = createApi({
    reducerPath: "productApi",
    baseQuery: fetchBaseQuery({
        baseUrl: "http://localhost:8080/api/v1/order",
        prepareHeaders: (headers, { getState }) => {
            const token = localStorage.getItem('token');
            if (token) {
                headers.set('authorization', `${token}`)
            }
            return headers
        },
    }),
    endpoints: (builder) => ({
        getMyCart: builder.query({
            query: (limit) => ({
                method: 'GET',
                url: `/search?limit=10`,
                withCredentials: true,
            }),
        }),
        getBestProducts: builder.query({
            query: (limit) => ({
                method: 'GET',
                url: `/search?limit=10`,
            }),
        }),
        getProductById: builder.query({
            query: (id) => ({
                method: 'GET',
                url: `get/${id}`,
            }),
        }),
        getImageById: builder.query({
            query: (id) => ({
                method: 'GET',
                url: `get/${id}`,
            }),
        })
    })
});

export const { useGetProductsQuery, useGetProductByIdQuery } = productApi;