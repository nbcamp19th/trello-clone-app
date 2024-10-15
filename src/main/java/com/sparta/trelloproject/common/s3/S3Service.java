package com.sparta.trelloproject.common.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sparta.trelloproject.common.exception.ForbiddenException;
import com.sparta.trelloproject.common.exception.ResponseCode;
import com.sparta.trelloproject.domain.card.dto.request.CardImageRequestDto;
import com.sparta.trelloproject.domain.card.entity.Card;
import com.sparta.trelloproject.domain.card.entity.CardImage;
import com.sparta.trelloproject.domain.card.repository.CardImageRepository;
import java.io.IOException;
import java.rmi.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponse;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Component
public class S3Service {

    private final AmazonS3Client amazonS3Client;
    private final CardImageRepository cardImageRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadFile(MultipartFile multipartFile, Card card) throws ServerException {
        String url = uploadFileToS3(multipartFile);

        CardImageRequestDto cardImageDto = createCardImageDto(multipartFile, url);

        cardImageRepository.save(CardImage.of(cardImageDto, card));
        return url;
    }

    @Transactional
    public String updateFile(MultipartFile multipartFile, Card card, String existFilePath)
        throws ServerException {
        if (existFilePath != null && !existFilePath.isEmpty()) {
            String existingFileName = existFilePath.substring(
                existFilePath.lastIndexOf("/") + 1);
            amazonS3Client.deleteObject(bucket, existingFileName);
        }

        String url = uploadFileToS3(multipartFile);

        CardImageRequestDto cardImageDto = createCardImageDto(multipartFile, url);

        CardImage cardImage = cardImageRepository.findByCardId(card.getId());

        if (cardImage != null) {
            cardImage.update(cardImageDto);
        } else {
            cardImageRepository.save(CardImage.of(cardImageDto, card));
        }
        return url;
    }

    private String uploadFileToS3(MultipartFile multipartFile) {
        try {
            String originalFileName = multipartFile.getOriginalFilename();
            String fileName = originalFileName.substring(0, originalFileName.lastIndexOf("."));

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(multipartFile.getSize());
            metadata.setContentType(multipartFile.getContentType());

            amazonS3Client.putObject(
                new PutObjectRequest(bucket, fileName, multipartFile.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead)
            );
            String url = amazonS3Client.getUrl(bucket, fileName).toString();
            return url;
        }
        catch (IOException e) {
            throw new ForbiddenException(ResponseCode.INVALID_UPLOAD);
        }
    }

    private CardImageRequestDto createCardImageDto(MultipartFile multipartFile, String url) {
        String originalFileName = multipartFile.getOriginalFilename();
        String fileName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        return new CardImageRequestDto(url, fileName, extension);
    }
}
