import {Metadata} from "next";
import React from "react";

import theme from "@/styles/theme/theme";
import {ReactQueryProvider} from "@/providers/ReactRouterProvider";
import {ThemeProvider} from "@mui/material";


export const metadata: Metadata = {
    title: "Shop",
    description:
        "",
};

export default function RootLayout({
                                       children,
                                   }: Readonly<{
    children: React.ReactNode;
}>) {
    return (
        <html lang="en">
        <ThemeProvider theme={theme}>
            <body>
            <ReactQueryProvider>
                {children}
            </ReactQueryProvider>
            </body>
        </ThemeProvider>
        </html>
    );
}
