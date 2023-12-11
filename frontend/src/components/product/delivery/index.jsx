import styles from "./index.module.scss";
import { Button } from "../../button";

export const ProductDelivery = () => {
  return (
    <div className={styles.root}>
      <h1>Доставка</h1>
      <span>
        <span className={styles.post}>
          <img width={30} src="/assets/post.svg" />
          <p className={styles.postname}>Нова Пошта</p>
        </span>
        <p>
          Дізнайся про доставку в <a href="#">твій регіон</a>
        </p>
      </span>
      <Button variation={"black"} text={"Дивитись всі міста"} />
    </div>
  );
};
