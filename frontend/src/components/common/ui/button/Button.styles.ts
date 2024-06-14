import { SxProps, Theme } from "@mui/material";
import { ButtonColor, ButtonState, ButtonVariant } from "./types";
import getColors from "./utils/get-color";

export const button = (
    color: ButtonColor,
    variant: ButtonVariant
): SxProps<Theme> => ({
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    borderRadius: "15px",
    width: "100%",
    gap: "8px",
    textTransform: "none",
    padding: "16px 32px",

    color: getColors(color, variant, ButtonState.DEFAULT).textColor,
    backgroundColor: getColors(color, variant, ButtonState.DEFAULT)
        .backgroundColor,
    borderColor: getColors(color, variant, ButtonState.DEFAULT).borderColor,
    "&:hover": {
        color: getColors(color, variant, ButtonState.HOVER).textColor,
        backgroundColor: getColors(color, variant, ButtonState.HOVER)
            .backgroundColor,
        borderColor: getColors(color, variant, ButtonState.HOVER).borderColor,
    },
    "&:active": {
        color: getColors(color, variant, ButtonState.ACTIVE).textColor,
        backgroundColor: getColors(color, variant, ButtonState.ACTIVE)
            .backgroundColor,
        borderColor: getColors(color, variant, ButtonState.ACTIVE).borderColor,
    },
    "&:disabled": {
        backgroundColor: getColors(color, variant, ButtonState.DISABLED)
            .backgroundColor,
        borderColor: getColors(color, variant, ButtonState.DISABLED).borderColor,
        color: getColors(color, variant, ButtonState.DISABLED).colorDisabled,
        cursor: "not-allowed",
    },
});

export const icon: SxProps<Theme> = {
    width: "16px",
    height: "16px",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    alignContent: "center",
    padding: "0",
    border: "none",
    svg: {
        width: "16px",
        height: "16px",
    },
};
