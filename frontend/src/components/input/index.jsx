import styles from "./index.module.scss";
import {useState} from "react";

export const Input = ({ placeholder, style}) => {
    const [search, setSearch] = useState(false)

  return (
    <div className={styles.container}>
      {search && <div className={styles.wrapper}></div>}
        <div className={styles.refContainer}>
      <div className={styles.root}>
        <input
          onClick={() => setSearch(!search)}
          placeholder={placeholder}
          style={style}
        ></input>
        <img src="/assets/header/search.svg" />
      </div>
      {search && (
        <div className={styles.search}>
          <p>dsaodnsajkdjskabjdsasads</p>
          <p>dsads</p>
          <p>dsads</p>
          <p>dsads</p>
        </div>
      )}
        </div>
    </div>
  );
};
