import { configureStore } from "@reduxjs/toolkit";
import { authApi } from "../services/auth.js";
import authReducer from './slices/authSlice.js'
export const store = configureStore({
  reducer: {
    auth: authReducer,
    [authApi.reducerPath]: authApi.reducer,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().concat(authApi.middleware),
});
