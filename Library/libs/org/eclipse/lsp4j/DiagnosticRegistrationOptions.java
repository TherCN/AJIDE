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

import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * Diagnostic registration options.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class DiagnosticRegistrationOptions extends AbstractTextDocumentRegistrationAndWorkDoneProgressOptions {
  /**
   * The id used to register the request. The id can be used to deregister
   * the request again. See also {@link Registration#id}.
   */
  private String id;

  /**
   * An optional identifier under which the diagnostics are
   * managed by the client.
   */
  private String identifier;

  /**
   * Whether the language has inter file dependencies meaning that
   * editing code in one file can result in a different diagnostic
   * set in another file. Inter file dependencies are common for
   * most programming languages and typically uncommon for linters.
   */
  private boolean interFileDependencies;

  /**
   * The server provides support for workspace diagnostics as well.
   */
  private boolean workspaceDiagnostics;

  public DiagnosticRegistrationOptions() {
  }

  public DiagnosticRegistrationOptions(final String id) {
    this.id = id;
  }

  public DiagnosticRegistrationOptions(final boolean interFileDependencies, final boolean workspaceDiagnostics) {
    this.interFileDependencies = interFileDependencies;
    this.workspaceDiagnostics = workspaceDiagnostics;
  }

  /**
   * The id used to register the request. The id can be used to deregister
   * the request again. See also {@link Registration#id}.
   */
  public String getId() {
    return this.id;
  }

  /**
   * The id used to register the request. The id can be used to deregister
   * the request again. See also {@link Registration#id}.
   */
  public void setId(final String id) {
    this.id = id;
  }

  /**
   * An optional identifier under which the diagnostics are
   * managed by the client.
   */
  public String getIdentifier() {
    return this.identifier;
  }

  /**
   * An optional identifier under which the diagnostics are
   * managed by the client.
   */
  public void setIdentifier(final String identifier) {
    this.identifier = identifier;
  }

  /**
   * Whether the language has inter file dependencies meaning that
   * editing code in one file can result in a different diagnostic
   * set in another file. Inter file dependencies are common for
   * most programming languages and typically uncommon for linters.
   */
  public boolean isInterFileDependencies() {
    return this.interFileDependencies;
  }

  /**
   * Whether the language has inter file dependencies meaning that
   * editing code in one file can result in a different diagnostic
   * set in another file. Inter file dependencies are common for
   * most programming languages and typically uncommon for linters.
   */
  public void setInterFileDependencies(final boolean interFileDependencies) {
    this.interFileDependencies = interFileDependencies;
  }

  /**
   * The server provides support for workspace diagnostics as well.
   */
  public boolean isWorkspaceDiagnostics() {
    return this.workspaceDiagnostics;
  }

  /**
   * The server provides support for workspace diagnostics as well.
   */
  public void setWorkspaceDiagnostics(final boolean workspaceDiagnostics) {
    this.workspaceDiagnostics = workspaceDiagnostics;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("id", this.id);
    b.add("identifier", this.identifier);
    b.add("interFileDependencies", this.interFileDependencies);
    b.add("workspaceDiagnostics", this.workspaceDiagnostics);
    b.add("workDoneProgress", getWorkDoneProgress());
    b.add("documentSelector", getDocumentSelector());
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
    DiagnosticRegistrationOptions other = (DiagnosticRegistrationOptions) obj;
    if (this.id == null) {
      if (other.id != null)
        return false;
    } else if (!this.id.equals(other.id))
      return false;
    if (this.identifier == null) {
      if (other.identifier != null)
        return false;
    } else if (!this.identifier.equals(other.identifier))
      return false;
    if (other.interFileDependencies != this.interFileDependencies)
      return false;
    if (other.workspaceDiagnostics != this.workspaceDiagnostics)
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.id== null) ? 0 : this.id.hashCode());
    result = prime * result + ((this.identifier== null) ? 0 : this.identifier.hashCode());
    result = prime * result + (this.interFileDependencies ? 1231 : 1237);
    return prime * result + (this.workspaceDiagnostics ? 1231 : 1237);
  }
}
