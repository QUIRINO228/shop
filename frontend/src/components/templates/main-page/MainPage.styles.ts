import { SxProps, Theme } from "@mui/material/styles";
import theme from "@/styles/theme/theme";

export const welcome: SxProps<Theme> = {
    position: 'relative',
    width: '100%',
    height: '100vh',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    padding: 0,
    margin: 0,
};

export const welcomeTextBox: SxProps<Theme> = {
    position: 'absolute',
    top: '284px',
    left: '280px',
    padding: '12.5px',
};

export const welcomeText: SxProps<Theme> = {
    marginBottom: "16px",
};

export const button: SxProps<Theme> = {
    marginTop: "16px",
};

export const cardContainer: SxProps<Theme> = {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    backgroundColor: theme.palette.violet[300],
    marginTop: '20px',
    padding: '85px 0 100px 0',
};

export const category: SxProps<Theme> = {
    textAlign: 'center',
    marginBottom: '20px',
    color: theme.palette.white.main,
};

export const cardRow: SxProps<Theme> = {
    display: 'flex',
    justifyContent: 'center',
    gap: '24px',
    padding: '55px 70px 0 70px',
};
