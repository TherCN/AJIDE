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

import com.google.gson.annotations.JsonAdapter;
import org.eclipse.lsp4j.jsonrpc.json.adapters.JsonElementTypeAdapter;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * A notebook cell.
 * <p>
 * A cell's document URI must be unique across ALL notebook
 * cells and can therefore be used to uniquely identify a
 * notebook cell or the cell's text document.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class NotebookCell {
  /**
   * The cell's kind
   */
  @NonNull
  private NotebookCellKind kind;

  /**
   * The URI of the cell's text document
   * content.
   */
  @NonNull
  private String document;

  /**
   * Additional metadata stored with the cell.
   */
  @JsonAdapter(JsonElementTypeAdapter.Factory.class)
  private Object metadata;

  /**
   * Additional execution summary information
   * if supported by the client.
   */
  private ExecutionSummary executionSummary;

  public NotebookCell() {
  }

  public NotebookCell(@NonNull final NotebookCellKind kind, @NonNull final String document) {
    this.kind = Preconditions.<NotebookCellKind>checkNotNull(kind, "kind");
    this.document = Preconditions.<String>checkNotNull(document, "document");
  }

  /**
   * The cell's kind
   */
  @NonNull
  public NotebookCellKind getKind() {
    return this.kind;
  }

  /**
   * The cell's kind
   */
  public void setKind(@NonNull final NotebookCellKind kind) {
    this.kind = Preconditions.checkNotNull(kind, "kind");
  }

  /**
   * The URI of the cell's text document
   * content.
   */
  @NonNull
  public String getDocument() {
    return this.document;
  }

  /**
   * The URI of the cell's text document
   * content.
   */
  public void setDocument(@NonNull final String document) {
    this.document = Preconditions.checkNotNull(document, "document");
  }

  /**
   * Additional metadata stored with the cell.
   */
  public Object getMetadata() {
    return this.metadata;
  }

  /**
   * Additional metadata stored with the cell.
   */
  public void setMetadata(final Object metadata) {
    this.metadata = metadata;
  }

  /**
   * Additional execution summary information
   * if supported by the client.
   */
  public ExecutionSummary getExecutionSummary() {
    return this.executionSummary;
  }

  /**
   * Additional execution summary information
   * if supported by the client.
   */
  public void setExecutionSummary(final ExecutionSummary executionSummary) {
    this.executionSummary = executionSummary;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("kind", this.kind);
    b.add("document", this.document);
    b.add("metadata", this.metadata);
    b.add("executionSummary", this.executionSummary);
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
    NotebookCell other = (NotebookCell) obj;
    if (this.kind == null) {
      if (other.kind != null)
        return false;
    } else if (!this.kind.equals(other.kind))
      return false;
    if (this.document == null) {
      if (other.document != null)
        return false;
    } else if (!this.document.equals(other.document))
      return false;
    if (this.metadata == null) {
      if (other.metadata != null)
        return false;
    } else if (!this.metadata.equals(other.metadata))
      return false;
    if (this.executionSummary == null) {
      if (other.executionSummary != null)
        return false;
    } else if (!this.executionSummary.equals(other.executionSummary))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.kind== null) ? 0 : this.kind.hashCode());
    result = prime * result + ((this.document== null) ? 0 : this.document.hashCode());
    result = prime * result + ((this.metadata== null) ? 0 : this.metadata.hashCode());
    return prime * result + ((this.executionSummary== null) ? 0 : this.executionSummary.hashCode());
  }
}
