import styles from "./index.module.scss";
import { Input } from "../../components/input";
import { Button } from "../../components/button";
import {useEffect, useState} from "react";
import {useLoginUserMutation} from "../../services/auth.js";
import {useDispatch} from "react-redux";
import {setToken} from "../../store/slices/authSlice.js";

export const LoginPage = () => {
  const dispatch = useDispatch()

  const initialState = {
    email: '',
    password: ''
  }
  const [loginUser, {data, isSuccess, isError, error}] = useLoginUserMutation()

  const [formValue, setFormValue] = useState(
     initialState
  )
  const handleChange = (e) => {
    setFormValue({...formValue, [e.target.name]: e.target.value})
  }
  const handleLogin = async () => {
    await loginUser({
      email: formValue.email,
      password: formValue.password
    })
  }
  const action = async (e) => {
    e.preventDefault();
    await handleLogin()
  };

  useEffect(() => {
    if(isSuccess){
      dispatch(setToken(data.access_token))
    }

  }, [isSuccess])

  return (
    <div className={styles.root}>
      <form onSubmit={action}>
        <div className={styles.container}>
          <h1 className={styles.title}>Вхід</h1>
          <Input value={formValue.email} onChange={handleChange} name='email' placeholder="Email" img={"./assets/email.svg"} />
          <Input value={formValue.password} onChange={handleChange} name='password' placeholder="Password" img={"./assets/eye.svg"} />
          <div className={styles.button}>
            <Button variation="original" text={"Підтвердити"} />
            <p>
              Не маєш аккаунту? <span>Створити</span>
            </p>
          </div>
        </div>
      </form>
    </div>
  );
};
