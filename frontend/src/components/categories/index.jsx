import styles from "./index.module.scss";
import { Button } from "../button";

export const Categories = () => {
  const categories = [
    "Акції",
    "Ноутбуки та компʼютери",
    "Одежа та краса",
    "Товари для геймінгу",
    "Смартфони",
  ];
  return (
    <div className={styles.root}>
      <div className={styles.container}>
        <div className={styles.categories}>
          {categories.map((category) => (
            <p>{category}</p>
          ))}
        </div>
        <Button
          variation={"original"}
          text={"Весь каталог"}
          img={"/assets/icons/catalog.svg"}
        />
      </div>
    </div>
  );
};
