<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema">

    <xsl:output method="xml" encoding="UTF-8" indent="yes" />

    <xsl:template name="getCarsByCountry">
        <xsl:param name="countryParam" as="xs:string"/> 
        <xsl:param name="elementNameParam" />
        <xsl:for-each select="/Warehouse/Cars/Car">
            <xsl:if test="@country = $countryParam">
                <xsl:element name="{$elementNameParam}">
                    <xsl:attribute name="car" select="@name" />
                    <xsl:value-of select="@price" />
                </xsl:element>
            </xsl:if>
        </xsl:for-each>

    </xsl:template>

    <xsl:template match="/">
        <Summary>
            <xsl:call-template name="getCarsByCountry">
                <xsl:with-param name="countryParam" select="'Germany'" /> 
                <xsl:with-param name="elementNameParam" select="'GermanCars'" />
            </xsl:call-template>
             <xsl:call-template name="getCarsByCountry">
                <xsl:with-param name="countryParam" select="'Japan'" /> 
                <xsl:with-param name="elementNameParam" select="'JapanCars'" />
            </xsl:call-template>
        </Summary>
    </xsl:template>

</xsl:stylesheet>