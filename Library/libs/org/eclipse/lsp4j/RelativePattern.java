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

import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * A relative pattern is a helper to construct glob patterns that are matched
 * relatively to a base URI. The common value for a {@link #baseUri} is a workspace
 * folder root, but it can be another absolute URI as well.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class RelativePattern {
  /**
   * A workspace folder or a base URI as a string to which this pattern will be matched
   * against relatively.
   */
  @NonNull
  private Either<WorkspaceFolder, String> baseUri;

  /**
   * The actual glob pattern.
   */
  @NonNull
  private String pattern;

  public RelativePattern() {
  }

  public RelativePattern(@NonNull final Either<WorkspaceFolder, String> baseUri, @NonNull final String pattern) {
    this.baseUri = Preconditions.<Either<WorkspaceFolder, String>>checkNotNull(baseUri, "baseUri");
    this.pattern = Preconditions.<String>checkNotNull(pattern, "pattern");
  }

  /**
   * A workspace folder or a base URI as a string to which this pattern will be matched
   * against relatively.
   */
  @NonNull
  public Either<WorkspaceFolder, String> getBaseUri() {
    return this.baseUri;
  }

  /**
   * A workspace folder or a base URI as a string to which this pattern will be matched
   * against relatively.
   */
  public void setBaseUri(@NonNull final Either<WorkspaceFolder, String> baseUri) {
    this.baseUri = Preconditions.checkNotNull(baseUri, "baseUri");
  }

  public void setBaseUri(final WorkspaceFolder baseUri) {
    if (baseUri == null) {
      Preconditions.checkNotNull(baseUri, "baseUri");
      this.baseUri = null;
      return;
    }
    this.baseUri = Either.forLeft(baseUri);
  }

  public void setBaseUri(final String baseUri) {
    if (baseUri == null) {
      Preconditions.checkNotNull(baseUri, "baseUri");
      this.baseUri = null;
      return;
    }
    this.baseUri = Either.forRight(baseUri);
  }

  /**
   * The actual glob pattern.
   */
  @NonNull
  public String getPattern() {
    return this.pattern;
  }

  /**
   * The actual glob pattern.
   */
  public void setPattern(@NonNull final String pattern) {
    this.pattern = Preconditions.checkNotNull(pattern, "pattern");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("baseUri", this.baseUri);
    b.add("pattern", this.pattern);
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
    RelativePattern other = (RelativePattern) obj;
    if (this.baseUri == null) {
      if (other.baseUri != null)
        return false;
    } else if (!this.baseUri.equals(other.baseUri))
      return false;
    if (this.pattern == null) {
      if (other.pattern != null)
        return false;
    } else if (!this.pattern.equals(other.pattern))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.baseUri== null) ? 0 : this.baseUri.hashCode());
    return prime * result + ((this.pattern== null) ? 0 : this.pattern.hashCode());
  }
}
