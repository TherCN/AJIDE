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

import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * A versioned notebook document identifier.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class VersionedNotebookDocumentIdentifier {
  /**
   * The version number of this notebook document.
   */
  private int version;

  /**
   * The notebook document's URI.
   */
  @NonNull
  private String uri;

  public VersionedNotebookDocumentIdentifier() {
  }

  public VersionedNotebookDocumentIdentifier(final int version, @NonNull final String uri) {
    this.version = version;
    this.uri = Preconditions.<String>checkNotNull(uri, "uri");
  }

  /**
   * The version number of this notebook document.
   */
  public int getVersion() {
    return this.version;
  }

  /**
   * The version number of this notebook document.
   */
  public void setVersion(final int version) {
    this.version = version;
  }

  /**
   * The notebook document's URI.
   */
  @NonNull
  public String getUri() {
    return this.uri;
  }

  /**
   * The notebook document's URI.
   */
  public void setUri(@NonNull final String uri) {
    this.uri = Preconditions.checkNotNull(uri, "uri");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("version", this.version);
    b.add("uri", this.uri);
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
    VersionedNotebookDocumentIdentifier other = (VersionedNotebookDocumentIdentifier) obj;
    if (other.version != this.version)
      return false;
    if (this.uri == null) {
      if (other.uri != null)
        return false;
    } else if (!this.uri.equals(other.uri))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + this.version;
    return prime * result + ((this.uri== null) ? 0 : this.uri.hashCode());
  }
}
