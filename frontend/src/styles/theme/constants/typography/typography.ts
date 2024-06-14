import { poppins } from '@/styles/fonts'
import {TypographyOptions} from "@mui/material/styles/createTypography";

const typography: TypographyOptions = {
    fontFamily: poppins.style.fontFamily,
    h1: {
        fontSize: 80,
        fontWeight: 700,
        lineHeight: "120px",
    },
    h2: {
        fontSize: 48,
        fontWeight: 700,
        lineHeight: "59px",
    },
    h3: {
        fontSize: 32,
        fontWeight: 700,
        lineHeight: "39px",
    },
    h4: {
        fontSize: 24,
        fontWeight: 700,
        lineHeight: "29px",
    },
    body1: {
        fontSize: 18,
        fontWeight: 500,
        lineHeight: "27px",
    },
    body2: {
        fontSize: 16,
        fontWeight: 400,
        lineHeight: "18px",
    },
};

export default typography;