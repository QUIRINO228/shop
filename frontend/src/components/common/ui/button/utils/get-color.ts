import {
    ButtonColor,
    ButtonColorsMap,
    ButtonColorsStruct,
    ButtonState,
    ButtonVariant,
} from "../types";

const buttonColorsMap: ButtonColorsMap = {
    [ButtonVariant.FILLED]: {
        [ButtonColor.PRIMARY]: {
            textColor: ["white.main", "white.main", "white.main"],
            backgroundColor: ["white.main", "white.main", "white.main", "white.main"],
            borderColor: ["transparent", "transparent", "transparent", "transparent"],
            textColorDisabled: "white.main",
        },
        [ButtonColor.BLACK]: {
            textColor: ["white.main", "white.main", "white.main"],
            backgroundColor: ["gray.900", "gray.300", "gray.400", "gray.100"],
            borderColor: ["transparent", "transparent", "transparent", "transparent"],
            textColorDisabled: "gray.300",
        },
        [ButtonColor.WHITE]: {
            textColor: ["gray.900", "gray.900", "gray.900"],
            backgroundColor: ["white.main", "gray.50", "gray.100", "gray.50"],
            borderColor: ["transparent", "transparent", "transparent", "transparent"],
            textColorDisabled: "white.main",
        },
    },

    [ButtonVariant.OUTLINE]: {
        [ButtonColor.PRIMARY]: {
            textColor: ["white.main", "white.main", "white.main"],
            backgroundColor: ["violet.400", "violet.500", "violet.300", "violet.400"],
            borderColor: ["violet.800", "violet.600", "violet.700", "violet.300"],
            textColorDisabled: "violet.300",
        },
    },
};

const stateMap: Record<ButtonState, number> = {
    [ButtonState.DEFAULT]: 0,
    [ButtonState.HOVER]: 1,
    [ButtonState.ACTIVE]: 2,
    [ButtonState.DISABLED]: 3,
};

const getColors = (
    color: ButtonColor,
    variant: ButtonVariant,
    state: ButtonState
) => {
    const stateIndex = stateMap[state];
    const variantColors = buttonColorsMap[variant] as Record<
        ButtonColor,
        ButtonColorsStruct
    >;
    return {
        textColor: variantColors[color].textColor[stateIndex],
        backgroundColor: variantColors[color].backgroundColor[stateIndex],
        borderColor: variantColors[color].borderColor[stateIndex],
        colorDisabled: variantColors[color].textColorDisabled,
    };
};

export default getColors;
