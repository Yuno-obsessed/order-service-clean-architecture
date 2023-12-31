import styles from "./index.module.scss";
import { IconCard } from "../icon/index.jsx";
import { cart } from "../../../public/assets/icons/svg.jsx";

export const Price = ({
  originalPrice,
  discountPercentage,
  discountedPrice,
  button = true,
}) => {

  return (
    <div className={styles.price}>
      <div className={styles.discount}>
        <p className={styles.originalPrice}>{originalPrice}zl</p>
        <p className={styles.discountprice}>{discountPercentage}%</p>
      </div>
      <div className={styles.buy}>
        <h1>
          {discountedPrice}zl
        </h1>
        {button && <IconCard ico={cart("35px", "35px")} />}
      </div>
    </div>
  );
};
