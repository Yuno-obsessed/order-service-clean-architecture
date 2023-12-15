import styles from "./index.module.scss";
import { SearchInput } from "../searchInput";
import { IconHeader } from "../icon/header";
import { useNavigate } from "react-router-dom";

export const Header = () => {
  const navigation = useNavigate();

  return (
    <div className={styles.root}>
      <div className={styles.container}>
        <h1
            data-test='header_title'
            onClick={() => {
            navigation("/");
          }}
          className={styles.title}
        >
          Rozetkded
        </h1>
        <SearchInput onClick={() => {}} placeholder={"Я шукаю..."} />
        <div className={styles.icons}>
          <div data-test='header_cart' onClick={() => navigation("/cart")} className={styles.icon}>
            <IconHeader  ico="/assets/header/header_cart.svg" />
            <p>Кошик</p>
          </div>
          <div data-test='header_user' onClick={() => navigation("/login")} className={styles.icon}>
            <IconHeader ico="/assets/header/header_user.svg" />
            <p>Профіль</p>
          </div>
          <div data-test='header_heart' onClick={() => navigation("/wishlist")} className={styles.icon}>
            <IconHeader ico="/assets/header/header_heart.svg" />
            <p>Бажане</p>
          </div>
          <div className={styles.mobile}>
            <div onClick={() => navigation("/")} data-test='header_main' className={styles.icon}>
              <IconHeader ico="/assets/header/header_house.svg" />
              <p>Головна</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
