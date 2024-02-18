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
 * A full document diagnostic report for a workspace diagnostic result.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class WorkspaceFullDocumentDiagnosticReport extends FullDocumentDiagnosticReport {
  /**
   * The URI for which diagnostic information is reported.
   */
  @NonNull
  private String uri;

  /**
   * The version number for which the diagnostics are reported.
   * If the document is not marked as open {@code null} can be provided.
   */
  private Integer version;

  public WorkspaceFullDocumentDiagnosticReport() {
  }

  public WorkspaceFullDocumentDiagnosticReport(@NonNull final List<Diagnostic> items, @NonNull final String uri, final Integer version) {
    super(items);
    this.uri = Preconditions.<String>checkNotNull(uri, "uri");
    this.version = version;
  }

  /**
   * The URI for which diagnostic information is reported.
   */
  @NonNull
  public String getUri() {
    return this.uri;
  }

  /**
   * The URI for which diagnostic information is reported.
   */
  public void setUri(@NonNull final String uri) {
    this.uri = Preconditions.checkNotNull(uri, "uri");
  }

  /**
   * The version number for which the diagnostics are reported.
   * If the document is not marked as open {@code null} can be provided.
   */
  public Integer getVersion() {
    return this.version;
  }

  /**
   * The version number for which the diagnostics are reported.
   * If the document is not marked as open {@code null} can be provided.
   */
  public void setVersion(final Integer version) {
    this.version = version;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("uri", this.uri);
    b.add("version", this.version);
    b.add("kind", getKind());
    b.add("resultId", getResultId());
    b.add("items", getItems());
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
    if (!super.equals(obj))
      return false;
    WorkspaceFullDocumentDiagnosticReport other = (WorkspaceFullDocumentDiagnosticReport) obj;
    if (this.uri == null) {
      if (other.uri != null)
        return false;
    } else if (!this.uri.equals(other.uri))
      return false;
    if (this.version == null) {
      if (other.version != null)
        return false;
    } else if (!this.version.equals(other.version))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.uri== null) ? 0 : this.uri.hashCode());
    return prime * result + ((this.version== null) ? 0 : this.version.hashCode());
  }
}
