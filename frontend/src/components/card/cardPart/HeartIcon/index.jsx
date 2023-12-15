import styles from './index.module.scss'
import {IconCard} from "../../../icon/index.jsx";
import {heart} from "../../../../../public/assets/icons/svg.jsx";

export const HeartIcon = ({ onClick }) => (
    <span onClick={onClick} className={styles.cardheart}>
        <IconCard ico={heart("30px", "30px")} />
    </span>
);