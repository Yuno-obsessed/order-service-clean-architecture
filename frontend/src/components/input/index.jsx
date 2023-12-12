import styles from "./index.module.scss";
import {memo, useState} from "react";

export const Input = memo(({ placeholder, value, name, onChange ,style, img }) => {
  const [seePass, setSeePass] = useState(false);
  const handleSeePassword = () => {
    if (placeholder.toLowerCase().includes("pass")) {
      setSeePass(!seePass);
    } else return 0;
  };
  return (
    <div className={styles.container}>
      <div className={styles.root}>
        <input
          name={name}
          value={value}
          onChange={onChange}
          type={seePass ? "password" : "initial"}
          placeholder={placeholder}
          style={style}
        ></input>
        <img
          onClick={handleSeePassword}
          src={!seePass ? img : "./assets/eye-slash.svg"}
        />
      </div>
    </div>
  );
})

