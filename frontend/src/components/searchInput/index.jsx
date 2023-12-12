import styles from "./index.module.scss";
import { useState } from "react";
import useComponentVisible from "../../hooks/outsideClick.js";

export const SearchInput = ({ placeholder, style }) => {
  const {setIsComponentVisible, isComponentVisible, ref} = useComponentVisible()

  return (
    <div className={styles.container}>
      {isComponentVisible && <div className={styles.wrapper}></div>}
      <div ref={ref} className={styles.refContainer}>
        <div className={styles.root}>
          <input
            onClick={() => setIsComponentVisible(!isComponentVisible)}
            placeholder={placeholder}
            style={style}
          ></input>
          <img src="/assets/header/search.svg" />
        </div>
        {isComponentVisible && (
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
