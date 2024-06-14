import { Color } from "@mui/material";

import "@mui/material/styles";

declare module "@mui/material/styles" {
  interface PaletteColor extends Color {}

  interface Palette {
    primary: Palette["primary"];
    gray: Palette["primary"];
    violet: Palette["primary"];
    info: Palette["primary"];
    white: Palette["primary"];
  }

  interface PaletteOptions {
    primary?: PaletteOptions["primary"];
    gray?: PaletteOptions["primary"];
    violet?: PaletteOptions["primary"];
    info?: PaletteOptions["primary"];
    white?: PaletteOptions["primary"];
  }
}
