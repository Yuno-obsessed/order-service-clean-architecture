import styles from "./index.module.scss";

export const IconHeader = ({ style, ico }) => {
  return (
    <div style={{ ...style }} className={styles.root}>
      <img src={ico} />
    </div>
  );
};
