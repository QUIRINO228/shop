import { SxProps, Theme } from "@mui/material/styles";
import theme from "@/styles/theme/theme";

export const header: SxProps<Theme> = {
    width: "100%",
    display: "flex",
    justifyContent: "center",
    backgroundColor: "white",
    boxShadow: "0 2px 4px rgba(0, 0, 0, 0.1)",
    padding: "8px",
    position: 'fixed',
    top: 0,
    left: 0,
    zIndex: 1000,
    background: "white",
};

export const container: SxProps<Theme> = {
    display: "flex",
    alignItems: "center",
    justifyContent: "space-between",
    maxWidth: "1200px",
    padding: "0 20px",
};

export const logo: SxProps<Theme> = {
    marginRight: "276px",
};

export const navLinks: SxProps<Theme> = {
    display: "flex",
    gap: "30px",
    justifyContent: "center",
    flexGrow: 1,
};

export const link = (theme: Theme): SxProps<Theme> => ({
    textDecoration: "none",
    color: "black",
    fontFamily: theme.typography.body1.fontFamily,
    "&:hover": {
        color: theme.palette.info.main,
    }
});

export const icons: SxProps<Theme> = {
    display: "flex",
    gap: "30px",
    marginLeft: "275px",
};

export const icon: SxProps<Theme> = {
    color: theme.palette.primary.dark,
}

export const lastIcon: SxProps<Theme> = {
    marginLeft: "94px",
};
