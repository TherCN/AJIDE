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
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * Client capabilities specific to the used markdown parser.
 * <p>
 * Since 3.16.0
 */
@SuppressWarnings("all")
public class MarkdownCapabilities {
  /**
   * The name of the parser.
   */
  @NonNull
  private String parser;

  /**
   * The version of the parser.
   */
  private String version;

  /**
   * A list of HTML tags that the client allows / supports in Markdown.
   * <p>
   * Since 3.17.0
   */
  private List<String> allowedTags;

  public MarkdownCapabilities() {
  }

  public MarkdownCapabilities(@NonNull final String parser) {
    this.parser = Preconditions.<String>checkNotNull(parser, "parser");
  }

  public MarkdownCapabilities(@NonNull final String parser, final String version) {
    this(parser);
    this.version = version;
  }

  /**
   * The name of the parser.
   */
  @NonNull
  public String getParser() {
    return this.parser;
  }

  /**
   * The name of the parser.
   */
  public void setParser(@NonNull final String parser) {
    this.parser = Preconditions.checkNotNull(parser, "parser");
  }

  /**
   * The version of the parser.
   */
  public String getVersion() {
    return this.version;
  }

  /**
   * The version of the parser.
   */
  public void setVersion(final String version) {
    this.version = version;
  }

  /**
   * A list of HTML tags that the client allows / supports in Markdown.
   * <p>
   * Since 3.17.0
   */
  public List<String> getAllowedTags() {
    return this.allowedTags;
  }

  /**
   * A list of HTML tags that the client allows / supports in Markdown.
   * <p>
   * Since 3.17.0
   */
  public void setAllowedTags(final List<String> allowedTags) {
    this.allowedTags = allowedTags;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("parser", this.parser);
    b.add("version", this.version);
    b.add("allowedTags", this.allowedTags);
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
    MarkdownCapabilities other = (MarkdownCapabilities) obj;
    if (this.parser == null) {
      if (other.parser != null)
        return false;
    } else if (!this.parser.equals(other.parser))
      return false;
    if (this.version == null) {
      if (other.version != null)
        return false;
    } else if (!this.version.equals(other.version))
      return false;
    if (this.allowedTags == null) {
      if (other.allowedTags != null)
        return false;
    } else if (!this.allowedTags.equals(other.allowedTags))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.parser== null) ? 0 : this.parser.hashCode());
    result = prime * result + ((this.version== null) ? 0 : this.version.hashCode());
    return prime * result + ((this.allowedTags== null) ? 0 : this.allowedTags.hashCode());
  }
}
