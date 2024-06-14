"use client"
import Header from "@/components/common/layout/header";
import {Box, Typography} from "@mui/material";
import Button from '@/components/common/ui/button/Button';
import Image from "next/image";
import theme from "@/styles/theme/theme";
import { ButtonColor, ButtonVariant } from "@/components/common/ui/button/types";

import * as styles from './MainPage.styles';

export default function MainPage() {
    return (
        <>
            <Header />
            <Box sx={styles.welcome}>
                <Image
                    alt="main page"
                    src="https://bucketforshop.s3.eu-north-1.amazonaws.com/home-page1.svg"
                    layout="fill"
                    objectFit="cover"
                    style={{ zIndex: -1, marginTop: "50px" }}
                />
                <Box sx={styles.welcomeTextBox}>
                    <Typography variant="body1" component="div" gutterBottom sx={styles.welcomeText}>
                        /Shop name/
                    </Typography>
                    <Typography variant="h1" color={theme.palette.violet[400]} component="div" gutterBottom sx={styles.welcomeText}>
                        WELCOME
                    </Typography>
                    <Typography variant="body1" component="div" gutterBottom sx={styles.welcomeText}>
                        All kind of electronics for all your needs.
                    </Typography>
                    <Button text="Shop Now" variant={ButtonVariant.OUTLINE} color={ButtonColor.PRIMARY} sx={styles.button} />
                </Box>
            </Box>

            <Box sx={styles.cardContainer}>
                <Typography variant="h1" component="div" sx={styles.category}>
                    Category
                </Typography>
                <Box sx={styles.cardRow}>
                    <Image
                        alt="Phones"
                        src="https://bucketforshop.s3.eu-north-1.amazonaws.com/PhonesCard.svg"
                        width={420}
                        height={420}
                        style={{marginRight: '24px' }}
                    />
                    <Image
                        alt="Tablets"
                        src="https://bucketforshop.s3.eu-north-1.amazonaws.com/TabletsCard.svg"
                        width={420}
                        height={420}
                        style={{marginRight: '24px' }}
                    />
                    <Image
                        alt="Laptops"
                        src="https://bucketforshop.s3.eu-north-1.amazonaws.com/LaptopsCard.svg"
                        width={420}
                        height={420}
                    />
                </Box>
                <Box sx={styles.cardRow}>
                    <Image
                        alt="Audio"
                        src="https://bucketforshop.s3.eu-north-1.amazonaws.com/EarphonesCard.svg"
                        width={420}
                        height={420}
                        style={{marginRight: '24px' }}
                    />
                    <Image
                        alt="Watches"
                        src="https://bucketforshop.s3.eu-north-1.amazonaws.com/WatchesCard.svg"
                        width={420}
                        height={420}
                        style={{marginRight: '24px' }}
                    />
                    <Image
                        alt="Other"
                        src="https://bucketforshop.s3.eu-north-1.amazonaws.com/OthersCard.svg"
                        width={420}
                        height={420}
                    />
                </Box>
            </Box>
        </>
    );
}