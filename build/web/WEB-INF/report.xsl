<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : report.xsl
    Created on : March 27, 2020, 10:01 AM
    Author     : HP
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <table border="1" width="50%">
            <tr>
                <th>Brand</th>
                <th>Quantity</th>
            </tr>
            <xsl:for-each select="brands/product">
                <tr>
                    <td>
                        <xsl:value-of select="brand"/>
                    </td>
                    <td>
                        <xsl:value-of select="count"/>
                    </td>
                </tr>
                
            
            </xsl:for-each>
        </table>
    </xsl:template>

</xsl:stylesheet>
