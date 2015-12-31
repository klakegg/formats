package net.klakegg.formats.mobi.meta;

import net.klakegg.formats.common.util.ByteArrayReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExthHeader implements Serializable {

    private static final long serialVersionUID = -1947634910151549084L;

    private static Logger logger = LoggerFactory.getLogger(ExthHeader.class);

    private String author; // 100
    private String publisher; // 101
    private String imprint; // 102
    private String description; // 103
    private String isbn; // 104
    private List<String> subjects; // 105
    private String publishingdate; // 106
    private String review; // 107
    private String contributor; // 108
    private String rights; // 109
    private String subjectcode; // 110
    private String type; // 111
    private String source; // 112
    private String asin; // 113
    private String versionnumber; // 114
    private Boolean sample; // 115
    private Integer startreading; // 116
    private Boolean adult; // 117

    private String cdetype; // 501
    private String lastupdatetime; // 502
    private String updatedtitle; // 503
    private String language; // 524
    private String alignment; // 525
    private String creatorBuildNumber; // 535

    public ExthHeader(ByteArrayReader reader) {
        int records = reader.getInt(8);

        reader = reader.getReader(12);
        for (int i = 0; i < records; i++) {
            int length = reader.getInt(4);
            ByteArrayReader value = reader.getReader(8, length - 8);

            switch (reader.getInt(0)) {
                case 100:
                    author = value.getStr();
                    break;
                case 101:
                    publisher = value.getStr();
                    break;
                case 102:
                    imprint = value.getStr();
                    break;
                case 103:
                    description = value.getStr();
                    break;
                case 104:
                    isbn = value.getStr();
                    break;
                case 105:
                    if (subjects == null)
                        subjects = new ArrayList<>();
                    subjects.add(value.getStr());
                    break;
                case 106:
                    publishingdate = value.getStr();
                    break;
                case 107:
                    review = value.getStr();
                    break;
                case 108:
                    contributor = value.getStr();
                    break;
                case 109:
                    rights = value.getStr();
                    break;
                case 110:
                    subjectcode = value.getStr();
                    break;
                case 111:
                    type = value.getStr();
                    break;
                case 112:
                    source = value.getStr();
                    break;
                case 113:
                    asin = value.getStr();
                    break;
                case 114:
                    versionnumber = value.getStr();
                    break;
                case 115:
                    sample = value.getInt(0) == 1;
                    break;
                case 116:
                    startreading = value.getInt(0);
                    break;
                case 117:
                    adult = "yes".equals(value.getStr());
                    break;

                case 501:
                    cdetype = value.getStr();
                    break;
                case 502:
                    lastupdatetime = value.getStr();
                    break;
                case 503:
                    updatedtitle = value.getStr();
                    break;
                case 524:
                    language = value.getStr();
                    break;
                case 525:
                    alignment = value.getStr();
                    break;
                case 535:
                    creatorBuildNumber = value.getStr();
                    break;

                default:
                    logger.info("{}: {}", reader.getInt(0), value.getStr());
                    break;
            }

            reader = reader.getReader(length);
        }
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getImprint() {
        return imprint;
    }

    public String getDescription() {
        return description;
    }

    public String getIsbn() {
        return isbn;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public String getPublishingdate() {
        return publishingdate;
    }

    public String getReview() {
        return review;
    }

    public String getContributor() {
        return contributor;
    }

    public String getRights() {
        return rights;
    }

    public String getSubjectcode() {
        return subjectcode;
    }

    public String getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    public String getAsin() {
        return asin;
    }

    public String getVersionnumber() {
        return versionnumber;
    }

    public Boolean getSample() {
        return sample;
    }

    public Integer getStartreading() {
        return startreading;
    }

    public Boolean getAdult() {
        return adult;
    }

    public String getCdetype() {
        return cdetype;
    }

    public String getLastupdatetime() {
        return lastupdatetime;
    }

    public String getUpdatedtitle() {
        return updatedtitle;
    }

    public String getLanguage() {
        return language;
    }

    public String getAlignment() {
        return alignment;
    }

    public String getCreatorBuildNumber() {
        return creatorBuildNumber;
    }

    @Override
    public String toString() {
        return "ExthHeader{" +
                "author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", imprint='" + imprint + '\'' +
                ", description='" + description + '\'' +
                ", isbn='" + isbn + '\'' +
                ", subjects=" + subjects +
                ", publishingdate='" + publishingdate + '\'' +
                ", review='" + review + '\'' +
                ", contributor='" + contributor + '\'' +
                ", rights='" + rights + '\'' +
                ", subjectcode='" + subjectcode + '\'' +
                ", type='" + type + '\'' +
                ", source='" + source + '\'' +
                ", asin='" + asin + '\'' +
                ", versionnumber='" + versionnumber + '\'' +
                ", sample=" + sample +
                ", startreading=" + startreading +
                ", adult=" + adult +
                ", cdetype='" + cdetype + '\'' +
                ", lastupdatetime='" + lastupdatetime + '\'' +
                ", updatedtitle='" + updatedtitle + '\'' +
                ", language='" + language + '\'' +
                ", alignment='" + alignment + '\'' +
                ", creatorBuildNumber='" + creatorBuildNumber + '\'' +
                '}';
    }
}
