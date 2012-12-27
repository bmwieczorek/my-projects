<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema">

    <xsl:output method="xml" encoding="UTF-8" indent="yes" />

    <xsl:template match="/">
        <summary>
            <xsl:for-each select="//Car">
                <xsl:value-of select="@name" />
                <xsl:if test="position()!=last()">
                    <xsl:text>,</xsl:text>
                </xsl:if>
                <xsl:if test="position()=last()">
                    <xsl:text>.</xsl:text>
                </xsl:if>
            </xsl:for-each>
        </summary>
    </xsl:template>

</xsl:stylesheet>