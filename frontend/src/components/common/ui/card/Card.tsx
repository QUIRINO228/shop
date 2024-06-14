import { Box, Typography } from "@mui/material";
import Image from "next/image";

import * as styles from './Card.styles'

interface CardProps {
    title: string;
    imageUrl: string;
    imageSize: {
        width: number;
        height: number;
    };
}

const Card: React.FC<CardProps> = ({ title, imageUrl, imageSize }) => {
    return (
        <Box sx={styles.card}>
            <Typography variant="h1" sx={styles.cardTitle}>
                {title}
            </Typography>
            <Image
                alt={`Card ${title}`}
                src={imageUrl}
                width={imageSize.width}
                height={imageSize.height}
                style={{width: '100%', height: 'auto', borderRadius: '8px',}}
            />
        </Box>
    );
};

export default Card;
