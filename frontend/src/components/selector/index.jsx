import styles from "./index.module.scss";

export const Selector = ({}) => {
  return (
    <div className={styles.root}>
      <label>
        <select>
          <option value="affordable">Від дешевого до дорогого</option>
          <option value="expensive">Від дорогого до дешевого</option>
        </select>
      </label>
    </div>
  );
};
