import styles from './index.module.scss'

export const ProductTitle = ({ productName }) => (
    <h1 className={styles.cardTitle}>{productName}</h1>
);