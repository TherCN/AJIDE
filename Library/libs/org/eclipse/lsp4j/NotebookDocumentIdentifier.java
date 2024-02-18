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
 * A literal to identify a notebook document in the client.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class NotebookDocumentIdentifier {
  /**
   * The notebook document's URI.
   */
  @NonNull
  private String uri;

  public NotebookDocumentIdentifier() {
  }

  public NotebookDocumentIdentifier(@NonNull final String uri) {
    this.uri = Preconditions.<String>checkNotNull(uri, "uri");
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
    NotebookDocumentIdentifier other = (NotebookDocumentIdentifier) obj;
    if (this.uri == null) {
      if (other.uri != null)
        return false;
    } else if (!this.uri.equals(other.uri))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return 31 * 1 + ((this.uri== null) ? 0 : this.uri.hashCode());
  }
}
