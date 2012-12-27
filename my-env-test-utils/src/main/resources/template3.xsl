<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema">

    <xsl:output method="xml" encoding="UTF-8" indent="yes" />

    <xsl:template match="*[@price and @price>10]">
        <BigPrice>
            <xsl:value-of select="@price" />
        </BigPrice>
    </xsl:template>
    
    <xsl:template match="Car">
        <item>
            <xsl:value-of select="concat('Car ',@name,' manufactured in ',@country,' costs ',@price)" />
        </item>
    </xsl:template>

    <xsl:template match="Bike">
        <item>
            <xsl:value-of select="concat(local-name(),name())" />
        </item>
    </xsl:template>

    <xsl:template match="/">
        <Summary>
            <xsl:apply-templates />
        </Summary>
    </xsl:template>

</xsl:stylesheet>