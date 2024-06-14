import React, { MouseEventHandler, ReactNode } from "react";
import {
    Box,
    Button as MUIButton,
    SxProps,
    Theme,
    Typography,
} from "@mui/material";

import mergeSx from "@/lib/utils/mergeSx";

import * as styles from './Button.styles'
import { ButtonColor, ButtonVariant } from "./types";

interface ButtonProps {
    text?: string;
    variant?: ButtonVariant;
    color?: ButtonColor;
    sx?: SxProps<Theme>;
    icon?: ReactNode;
    disabled?: boolean;
    onClick?: MouseEventHandler<HTMLButtonElement>;
    type?: "button" | "submit";
}

const Button: React.FC<ButtonProps> = ({
    text,
    variant = ButtonVariant.OUTLINE,
    color = ButtonColor.PRIMARY,
    icon,
    sx={},
    disabled = false,
    type='button',
    ...rest
}) => {
    return (
        <MUIButton
            sx={mergeSx(styles.button(color, variant), sx)}
            type={type}
            {...rest}
            disabled={disabled}
        >
            <Typography variant="body1">{text}</Typography>
            {icon && <Box sx={styles.icon}>{icon}</Box>}
        </MUIButton>
    );
};

export default Button;
