import styles from "./index.module.scss";
import { IconCard } from "../icon";
import { heart } from "../../../public/assets/icons/svg.jsx";
import { Price } from "../price";

export const Card = ({ imageUrl, productName, onClick, price }) => {
  return (
    <div onClick={onClick} className={styles.root}>
      <div className={styles.container}>
        <div className={styles.card}>
          <span className={styles.cardheart}>
            <IconCard ico={heart("30px", "30px")} />
          </span>
          <img className={styles.cardImage} src={imageUrl} />
          <h1 className={styles.cardTitle}>{productName}</h1>
          <Price
            discountPercentage={price.discountPercentage}
            originalPrice={price.originalPrice}
            currency={price.currency}
            discountedPrice={price.discountedPrice}
          />
        </div>
      </div>
    </div>
  );
};
