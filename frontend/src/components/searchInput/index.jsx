import styles from "./index.module.scss";
import useComponentVisible from "../../hooks/outsideClick.js";
import { useState, useEffect, useCallback } from "react";
import { useGetProductByNameQuery } from "../../api/product.js";
import debounce from 'lodash.debounce';
import {useNavigate} from "react-router-dom";

export const SearchInput = ({ placeholder, style }) => {
  const { setIsComponentVisible, isComponentVisible, ref } = useComponentVisible();
  const navigate = useNavigate()
  const [value, setValue] = useState('');
  const { data, error } = useGetProductByNameQuery({
    value: value
  });

  const changeHandler = (event) => {
    setValue(event.target.value);
  };

  const debouncedChangeHandler = useCallback(debounce(changeHandler, 300), []);


  return (
      <div className={styles.container}>
        {isComponentVisible && <div className={styles.wrapper}></div>}
        <div ref={ref} className={styles.refContainer}>
          <div className={styles.root}>
            <input
                onChange={debouncedChangeHandler}
                onFocus={() => setIsComponentVisible(!isComponentVisible)}
                placeholder={placeholder}
                style={style}
            ></input>
            <img src="/assets/header/search.svg" />
          </div>
          {isComponentVisible && (
              <div className={styles.search}>
                {data &&
                    data?.map(product => (
                        <p onClick={() => {
                          setIsComponentVisible(!isComponentVisible)
                          navigate(`product/${product?.product_id}`)
                        }}
                            key={product?.product_id}>{product.name}</p>
                    ))
                }
              </div>
          )}
        </div>
      </div>
  );
};
