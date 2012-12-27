<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema">

    <xsl:output method="xml" encoding="UTF-8" indent="yes" />

    <xsl:variable name="version" as="xs:string" select="Orders/@version" />
    <xsl:variable name="v1" as="xs:integer" select="10" />
    <xsl:variable name="v2" as="xs:integer">
        <xsl:value-of select="$v1 + 1" />
    </xsl:variable>

    <xsl:template match="/">
        <OrdersSummary>
            <Count>
                <xsl:value-of select="count(/Orders/Order)" />
            </Count>
            <RestrictedCount>
                <xsl:value-of select="count(/Orders/Order[@price = 30])" />
            </RestrictedCount>
            <TotalPrice>
                <total>
                    <xsl:value-of select="sum(/Orders/Order/@price)" />
                </total>
            </TotalPrice>
            <OtherTotalPrice>
                <xsl:for-each select="/Orders/Order">
                    <name>
                        <xsl:value-of select="@name" />
                    </name>
                </xsl:for-each>
            </OtherTotalPrice>
            <OtherTotalPrice>
                <xsl:for-each select="/Orders/Order">
                    <name>
                        <xsl:value-of select="." />
                    </name>
                </xsl:for-each>
            </OtherTotalPrice>

            <Version>
                <xsl:value-of select="$version" />
            </Version>
            <NewVersion>
                <xsl:value-of select="concat($version,'.1')" />
            </NewVersion>
            <V1>
                <xsl:value-of select="$v1" />
            </V1>
            <V2>
                <xsl:value-of select="$v2" />
            </V2>

            <xsl:variable name="tmp" as="xs:integer*" >
                <xsl:for-each select="/Orders/Order">
                    <name>
                        <xsl:value-of select="@price" />
                    </name>
                </xsl:for-each>
            </xsl:variable>
            <mysum><xsl:value-of select="sum($tmp)" /></mysum>
        </OrdersSummary>
    </xsl:template>

</xsl:stylesheet>