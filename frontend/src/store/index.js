import {configureStore, createListenerMiddleware, isAnyOf, isRejectedWithValue} from "@reduxjs/toolkit";
import { authApi } from "../api/auth.js";
import authReducer from './slices/authSlice.js'
import {productApi} from "../api/product.js";

export const store = configureStore({
  reducer: {
    auth: authReducer,
    [authApi.reducerPath]: authApi.reducer,
    [productApi.reducerPath]: productApi.reducer,

  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().concat(productApi.middleware,authApi.middleware)
});
