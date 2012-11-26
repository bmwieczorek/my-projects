<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema">

    <xsl:output method="xml" encoding="UTF-8" indent="yes" />

    <xsl:template match="/">
        <Shipment>
            <Amount>
                <xsl:value-of select="book/price" />
            </Amount>
            <Author>
                <Name><xsl:value-of select="book/author/@name" /></Name>
                <Surname><xsl:value-of select="book/author/@surname" /></Surname>
            </Author>
        </Shipment>
    </xsl:template>

</xsl:stylesheet>