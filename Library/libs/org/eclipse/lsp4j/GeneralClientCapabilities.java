/**
 * Copyright (c) 2016-2018 TypeFox and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */
package org.eclipse.lsp4j;

import java.util.List;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * General client capabilities.
 * <p>
 * Since 3.16.0
 */
@SuppressWarnings("all")
public class GeneralClientCapabilities {
  /**
   * Client capabilities specific to regular expressions.
   * <p>
   * Since 3.16.0
   */
  private RegularExpressionsCapabilities regularExpressions;

  /**
   * Client capabilities specific to the client's markdown parser.
   * <p>
   * Since 3.16.0
   */
  private MarkdownCapabilities markdown;

  /**
   * Client capability that signals how the client handles stale requests
   * (e.g. a request for which the client will not process the response
   * anymore since the information is outdated).
   * <p>
   * Since 3.17.0
   */
  private StaleRequestCapabilities staleRequestSupport;

  /**
   * The position encodings supported by the client. Client and server
   * have to agree on the same position encoding to ensure that offsets
   * (e.g. character position in a line) are interpreted the same on both
   * side.
   * <p>
   * To keep the protocol backwards compatible the following applies: if
   * the value 'utf-16' is missing from the array of position encodings
   * servers can assume that the client supports UTF-16. UTF-16 is
   * therefore a mandatory encoding.
   * <p>
   * If omitted it defaults to [{@link PositionEncodingKind#UTF16}].
   * <p>
   * Implementation considerations: since the conversion from one encoding
   * into another requires the content of the file / line the conversion
   * is best done where the file is read which is usually on the server
   * side.
   * <p>
   * See {@link PositionEncodingKind} for some predefined position encoding kinds.
   * <p>
   * Since 3.17.0
   */
  private List<String> positionEncodings;

  public GeneralClientCapabilities() {
  }

  /**
   * Client capabilities specific to regular expressions.
   * <p>
   * Since 3.16.0
   */
  public RegularExpressionsCapabilities getRegularExpressions() {
    return this.regularExpressions;
  }

  /**
   * Client capabilities specific to regular expressions.
   * <p>
   * Since 3.16.0
   */
  public void setRegularExpressions(final RegularExpressionsCapabilities regularExpressions) {
    this.regularExpressions = regularExpressions;
  }

  /**
   * Client capabilities specific to the client's markdown parser.
   * <p>
   * Since 3.16.0
   */
  public MarkdownCapabilities getMarkdown() {
    return this.markdown;
  }

  /**
   * Client capabilities specific to the client's markdown parser.
   * <p>
   * Since 3.16.0
   */
  public void setMarkdown(final MarkdownCapabilities markdown) {
    this.markdown = markdown;
  }

  /**
   * Client capability that signals how the client handles stale requests
   * (e.g. a request for which the client will not process the response
   * anymore since the information is outdated).
   * <p>
   * Since 3.17.0
   */
  public StaleRequestCapabilities getStaleRequestSupport() {
    return this.staleRequestSupport;
  }

  /**
   * Client capability that signals how the client handles stale requests
   * (e.g. a request for which the client will not process the response
   * anymore since the information is outdated).
   * <p>
   * Since 3.17.0
   */
  public void setStaleRequestSupport(final StaleRequestCapabilities staleRequestSupport) {
    this.staleRequestSupport = staleRequestSupport;
  }

  /**
   * The position encodings supported by the client. Client and server
   * have to agree on the same position encoding to ensure that offsets
   * (e.g. character position in a line) are interpreted the same on both
   * side.
   * <p>
   * To keep the protocol backwards compatible the following applies: if
   * the value 'utf-16' is missing from the array of position encodings
   * servers can assume that the client supports UTF-16. UTF-16 is
   * therefore a mandatory encoding.
   * <p>
   * If omitted it defaults to [{@link PositionEncodingKind#UTF16}].
   * <p>
   * Implementation considerations: since the conversion from one encoding
   * into another requires the content of the file / line the conversion
   * is best done where the file is read which is usually on the server
   * side.
   * <p>
   * See {@link PositionEncodingKind} for some predefined position encoding kinds.
   * <p>
   * Since 3.17.0
   */
  public List<String> getPositionEncodings() {
    return this.positionEncodings;
  }

  /**
   * The position encodings supported by the client. Client and server
   * have to agree on the same position encoding to ensure that offsets
   * (e.g. character position in a line) are interpreted the same on both
   * side.
   * <p>
   * To keep the protocol backwards compatible the following applies: if
   * the value 'utf-16' is missing from the array of position encodings
   * servers can assume that the client supports UTF-16. UTF-16 is
   * therefore a mandatory encoding.
   * <p>
   * If omitted it defaults to [{@link PositionEncodingKind#UTF16}].
   * <p>
   * Implementation considerations: since the conversion from one encoding
   * into another requires the content of the file / line the conversion
   * is best done where the file is read which is usually on the server
   * side.
   * <p>
   * See {@link PositionEncodingKind} for some predefined position encoding kinds.
   * <p>
   * Since 3.17.0
   */
  public void setPositionEncodings(final List<String> positionEncodings) {
    this.positionEncodings = positionEncodings;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("regularExpressions", this.regularExpressions);
    b.add("markdown", this.markdown);
    b.add("staleRequestSupport", this.staleRequestSupport);
    b.add("positionEncodings", this.positionEncodings);
    return b.toString();
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    GeneralClientCapabilities other = (GeneralClientCapabilities) obj;
    if (this.regularExpressions == null) {
      if (other.regularExpressions != null)
        return false;
    } else if (!this.regularExpressions.equals(other.regularExpressions))
      return false;
    if (this.markdown == null) {
      if (other.markdown != null)
        return false;
    } else if (!this.markdown.equals(other.markdown))
      return false;
    if (this.staleRequestSupport == null) {
      if (other.staleRequestSupport != null)
        return false;
    } else if (!this.staleRequestSupport.equals(other.staleRequestSupport))
      return false;
    if (this.positionEncodings == null) {
      if (other.positionEncodings != null)
        return false;
    } else if (!this.positionEncodings.equals(other.positionEncodings))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.regularExpressions== null) ? 0 : this.regularExpressions.hashCode());
    result = prime * result + ((this.markdown== null) ? 0 : this.markdown.hashCode());
    result = prime * result + ((this.staleRequestSupport== null) ? 0 : this.staleRequestSupport.hashCode());
    return prime * result + ((this.positionEncodings== null) ? 0 : this.positionEncodings.hashCode());
  }
}
