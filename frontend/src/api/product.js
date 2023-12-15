import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import {baseQueryWithReauth} from "./baseQuery/index.js";

export const productApi = createApi({
    reducerPath: "productApi",
    baseQuery: baseQueryWithReauth({ baseUrl: 'http://localhost:8080/api/v1/product' }),
    endpoints: (builder) => ({
        getProducts: builder.query({
            query: () => ({
                method: 'GET',
                url: `/search?limit=10`,
            }),
        }),
        getBestProducts: builder.query({
            query: (limit) => ({
                method: 'GET',
                url: `/search?limit=10`,
            }),
        }),
        getProductByName: builder.query({
            query: ({value}) => ({
                method: 'GET',
                url: `/search?${value ? `name=${value}` : ''}`,
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
                url: `images/${id}?onlyMain=true`,
            }),
        })
    })
});

export const { useGetProductsQuery, useGetProductByNameQuery ,useGetImageByIdQuery, useGetProductByIdQuery } = productApi;