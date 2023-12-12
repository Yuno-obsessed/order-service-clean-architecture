import styles from "./index.module.scss";
import { useCheckTokenMutation } from "../../services/auth.js";
import { useEffect } from "react";
import {useNavigate} from "react-router-dom";

export const PrivateRoute = ({ children }) => {
    const navigate = useNavigate();
    const [checkToken, { data, isSuccess, isError, error }] = useCheckTokenMutation();

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            checkToken({ token: token });
        } else {
           navigate('/login')
        }
    }, []);

    useEffect(() => {
        if (isError) {
            navigate('/login')
        }
    }, [isError, error]);

    return isSuccess ? children : null;
};
