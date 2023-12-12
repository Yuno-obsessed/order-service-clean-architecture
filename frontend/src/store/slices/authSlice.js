import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    token: '',
    isActiveToken: false
};

const authSlice = createSlice({
    name: 'auth',
    initialState: initialState,
    reducers: {
      setToken: (state, action) => {
          localStorage.setItem('token', action.payload)
          state.isActiveToken = true
          state.token = action.payload
      }
    },
});

export const selectAuth = (state) => state.auth.token
export const {setToken} = authSlice.actions
export default authSlice.reducer