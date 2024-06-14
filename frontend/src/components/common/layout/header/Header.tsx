import {Box, Container, Link} from "@mui/material";
import * as styles from "./Header.styles";
import SearchIcon from '@mui/icons-material/Search';
import BalanceIcon from '@mui/icons-material/Balance';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import ShoppingCartOutlinedIcon from '@mui/icons-material/ShoppingCartOutlined';
import AccountCircleOutlinedIcon from '@mui/icons-material/AccountCircleOutlined';
import theme from "@/styles/theme/theme";

const Header = () => {
    return (
        <Box component="header" sx={styles.header}>
            <Container sx={styles.container}>
                <Box sx={styles.logo}>
                    <img src="logo.png" alt="Logo" />
                </Box>
                <Box sx={styles.navLinks}>
                    <Link href="#" sx={styles.link(theme)}>HOME</Link>
                    <Link href="#" sx={styles.link(theme)}>SHOP</Link>
                    <Link href="#" sx={styles.link(theme)}>SALE</Link>
                    <Link href="#" sx={styles.link(theme)}>FEEDBACK</Link>
                    <Link href="#" sx={styles.link(theme)}>CONTACTS</Link>
                </Box>
                <Box sx={styles.icons}>
                    <Link><SearchIcon sx={styles.icon}/></Link>
                    <Link><BalanceIcon sx={styles.icon}/></Link>
                    <Link><FavoriteBorderIcon sx={styles.icon}/></Link>
                    <Link><ShoppingCartOutlinedIcon sx={styles.icon}/></Link>
                </Box>
                <Box sx={styles.lastIcon}>
                    <Link><AccountCircleOutlinedIcon sx={styles.icon}/></Link>
                </Box>
            </Container>
        </Box>
    )
};

export default Header;
