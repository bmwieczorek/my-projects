<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:fun="http://ticketing.sabre.com">

    <xsl:output method="xml" encoding="UTF-8" indent="yes" />

    <xsl:function name="fun:decimalPlacesCount" as="xs:int" >
        <xsl:param name="value" as="xs:string" />
        <xsl:value-of select="string-length(substring-after($value,'.'))" />
    </xsl:function>
    
    <xsl:function name="fun:decimalPlacesCountInXPath" as="xs:int">
        <xsl:param name="value" />
         <xsl:choose>
            <xsl:when test="$value">
                <xsl:value-of select="fun:decimalPlacesCount($value)" />
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="-1" />
            </xsl:otherwise>
        </xsl:choose>
    </xsl:function>

    <xsl:function name="fun:compareDecimalPlacesInStrings">
        <xsl:param name="value1" as="xs:string" />
        <xsl:param name="value2" as="xs:string" />
        <xsl:value-of select="fun:decimalPlacesCount($value1) = fun:decimalPlacesCount($value2)" />
    </xsl:function>

    <xsl:function name="fun:compareDecimalPlacesInXpaths" as="xs:boolean">
        <xsl:param name="value1" />
        <xsl:param name="value2" />
        <xsl:choose>
            <xsl:when test="$value1 and $value2">
                <xsl:value-of select="fun:compareDecimalPlacesInStrings($value1,$value2)" />
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="false()" />
            </xsl:otherwise>
        </xsl:choose>
    </xsl:function>

    <xsl:template match="/">
        <!-- <xsl:when test="string-length(substring-after(@v1,'.')) = string-length(substring-after(@v2,'.'))"> -->

		<xsl:variable name="firstFeeDecimalPlacesCount"  select="fun:decimalPlacesCountInXPath(//Fees/Fee[1]/FeeDetails/Base)"	/>

        <summary>

            <xsl:for-each select="//Fees/Fee">
                <result1>
                    <xsl:choose>
                        <xsl:when test="fun:decimalPlacesCountInXPath(FeeDetails/Base) = $firstFeeDecimalPlacesCount">
                            <xsl:value-of select="true()" />
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:value-of select="false()" />
                        </xsl:otherwise>
                    </xsl:choose>
							
					<xsl:value-of select="FeeDetails/Base" />
                    <xsl:text>has:  </xsl:text>
                    <xsl:value-of select="fun:decimalPlacesCountInXPath(FeeDetails/Base)" />
                    <xsl:text>decimal place(s) compared with: </xsl:text>
                    <xsl:value-of select="$firstFeeDecimalPlacesCount" />

                </result1>
            </xsl:for-each>
        </summary>
			<!--
            <r1>
                <xsl:value-of
                    select="fun:compareDecimalPlacesInXpaths(/RQ/Fees/Fee[1]/FeeDetails/Base/text(),/RQ/Miscellaneous/Fee/Base/Amount/text())" />
            </r1>
			-->
			<!--
			<r1>
				<xsl:value-of select="$firstFeeDecimalPlacesCount"  />
			</r1>
			-->

    </xsl:template>

</xsl:stylesheet>