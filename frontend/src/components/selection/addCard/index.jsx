import styles from "./index.module.scss";
import { Button } from "../../button";

export const AddCard = () => {
  return (
    <div className={styles.root}>
      <div className={styles.container}>
        <h1>Починай продавати разом з нами</h1>
        <Button text={"Додати товар"} variation={"black"} />
      </div>
    </div>
  );
};
