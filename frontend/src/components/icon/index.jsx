import styles from "./index.module.scss";

export const IconCard = ({ style, ico }) => {
  return (
    <div style={{ ...style }} className={styles.root}>
      {ico}
    </div>
  );
};
