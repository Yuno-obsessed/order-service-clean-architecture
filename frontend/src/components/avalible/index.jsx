import React, {memo} from "react";
import styles from "./index.module.scss";

export const Avalible = memo(({ atribute, availability, style }) => {
  const isAvailability = () => {
    return availability ? 'В наявності' : 'Немає'
  }
  return (
    <div data-test={atribute} style={{...style }} className={styles.root}>
      {isAvailability()}
    </div>
  );
});
