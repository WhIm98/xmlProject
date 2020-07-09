<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : indexProduct.xsl
    Created on : March 24, 2020, 8:58 PM
    Author     : HP
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" encoding="UTF-8"/>
    
    <xsl:template match="/">
        <xsl:for-each select="products/product">
            <div class="product">
                <img class="imgproduct">
                    <xsl:attribute name="src">
                        <xsl:value-of select="imageLink"/>
                    </xsl:attribute>
                </img>
                <p class="productname">
                    <xsl:value-of select="productName"/>
                </p>
                <a>
                    <xsl:attribute name="href">
                        <xsl:value-of select="productLink"/>
                    </xsl:attribute>
                    <p class="productprice">Price: <xsl:value-of select="price"/> VND</p>
                </a>
            </div>
            
        </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>
