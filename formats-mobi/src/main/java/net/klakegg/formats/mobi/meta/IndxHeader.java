package net.klakegg.formats.mobi.meta;

import net.klakegg.formats.common.util.ByteArrayReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndxHeader {

    private static Logger logger = LoggerFactory.getLogger(IndxHeader.class);

    public IndxHeader(ByteArrayReader reader) {
        /*
        logger.info("{} {}", reader.getBytes().length, reader.getStr());

        logger.info("Length: {}", reader.getInt(4));
        logger.info("Type: {}", reader.getInt(8));
        logger.info("Offset to IDXT: {}", reader.getInt(20));
        logger.info("Number of index record: {}", reader.getInt(24));
        logger.info("Index records: {}", reader.getInt(24));
        logger.info("Encoding: {}", reader.getInt(28));
        logger.info("Language: {}", reader.getInt(32));
        logger.info("Index entries: {}", reader.getInt(36));
        logger.info("Offset to ORDT: {}", reader.getInt(40));
        logger.info("Offset to LIGT: {}", reader.getInt(44));
        */
    }
}
