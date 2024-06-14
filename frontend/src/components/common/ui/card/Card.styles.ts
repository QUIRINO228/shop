import {SxProps, Theme} from "@mui/system";
import theme from "@/styles/theme/theme";

export const cardTitle: SxProps<Theme> = {
    fontWeight: 'bold',
    marginBottom: '16px',
    color: theme.palette.violet[300],
};

export const card: SxProps<Theme> = {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    borderRadius: '8px',
    boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
    padding: '16px',
    width: '541px',
    height: '541px',
    justifyContent: 'space-between',
    backgroundColor: theme.palette.white.main,
};